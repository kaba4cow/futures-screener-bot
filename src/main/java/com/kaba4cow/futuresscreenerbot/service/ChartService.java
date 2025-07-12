package com.kaba4cow.futuresscreenerbot.service;

import java.awt.BasicStroke;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.kaba4cow.futuresscreenerbot.config.properties.chart.ChartColorProperties;
import com.kaba4cow.futuresscreenerbot.config.properties.chart.ChartProperties;
import com.kaba4cow.futuresscreenerbot.domain.barseries.Bar;
import com.kaba4cow.futuresscreenerbot.domain.barseries.BarSeries;
import com.kaba4cow.futuresscreenerbot.util.FormattingUtil;
import com.kaba4cow.futuresscreenerbot.util.MathUtil;
import com.kaba4cow.futuresscreenerbot.util.tool.Symbol;
import com.kaba4cow.futuresscreenerbot.util.tool.TimeTracker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChartService {

	private final ChartProperties chartProperties;

	private final ChartColorProperties chartColorProperties;

	private final FuturesService futuresService;

	public RenderedImage createChart(Symbol symbol) {
		TimeTracker timeTracker = new TimeTracker().start();
		log.info("Rendering chart image for symbol {}...", symbol.toAssetsString());

		BarSeries barSeries = futuresService.getBarSeries(symbol, chartProperties.getInterval(), chartProperties.getBarCount());

		double currentClosePrice = barSeries.getLast().getClosePrice();
		double minLowPrice = Double.POSITIVE_INFINITY;
		double maxHighPrice = Double.NEGATIVE_INFINITY;
		int minLowIndex = 0;
		int maxHighIndex = 0;
		for (int i = 0; i <= barSeries.getLastIndex(); i++) {
			Bar bar = barSeries.getBar(i);
			double low = bar.getLowPrice();
			double high = bar.getHighPrice();
			if (low < minLowPrice) {
				minLowPrice = low;
				minLowIndex = i;
			}
			if (high > maxHighPrice) {
				maxHighPrice = high;
				maxHighIndex = i;
			}
		}

		int width = chartProperties.getBarCount() * chartProperties.getBarWidth() + chartProperties.getOffset();
		int height = 2 * width / 3;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setFont(chartProperties.getFont());
		FontMetrics metrics = graphics.getFontMetrics();

		int minY = 5 * metrics.getHeight();
		int maxY = height - 2 * metrics.getHeight();

		graphics.setColor(chartColorProperties.getBackground());
		graphics.fillRect(0, 0, width, height);

		int barX = chartProperties.getBarCount() * chartProperties.getBarWidth() - chartProperties.getBarWidth();
		for (int i = barSeries.getLastIndex(); i >= 0; i--) {
			Bar bar = barSeries.getBar(i);
			double open = bar.getOpenPrice();
			double close = bar.getClosePrice();
			double low = bar.getLowPrice();
			double high = bar.getHighPrice();

			double openY = MathUtil.map(open, minLowPrice, maxHighPrice, maxY, minY);
			double closeY = MathUtil.map(close, minLowPrice, maxHighPrice, maxY, minY);
			double bodyHeight = Math.abs(closeY - openY) + 1;

			double lowY = MathUtil.map(low, minLowPrice, maxHighPrice, maxY, minY);
			double highY = MathUtil.map(high, minLowPrice, maxHighPrice, maxY, minY);
			double tailHeight = Math.abs(highY - lowY) + 1;

			graphics.setColor(bar.isBullish() ? chartColorProperties.getBull() : chartColorProperties.getBear());
			graphics.fillRect(barX + chartProperties.getBarWidth() / 2, (int) highY, 1, (int) tailHeight);
			graphics.fillRect(barX + 1, (int) Math.min(openY, closeY), chartProperties.getBarWidth() - 2, (int) bodyHeight);
			barX -= chartProperties.getBarWidth();
		}

		graphics.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1f,
				chartProperties.getLineStroke(), 0.5f * chartProperties.getBarWidth()));
		{
			int lowX = minLowIndex * chartProperties.getBarWidth();
			graphics.setColor(chartColorProperties.getLine());
			graphics.drawLine(lowX, maxY, width, maxY);
			String lowText = FormattingUtil.number(minLowPrice, 8);
			graphics.setColor(chartColorProperties.getText());
			graphics.drawString(lowText, width - metrics.stringWidth(lowText), maxY - 1);
		}
		{
			int highX = maxHighIndex * chartProperties.getBarWidth();
			graphics.setColor(chartColorProperties.getLine());
			graphics.drawLine(highX, minY, width, minY);
			String highText = FormattingUtil.number(maxHighPrice, 8);
			graphics.setColor(chartColorProperties.getText());
			graphics.drawString(highText, width - metrics.stringWidth(highText), minY - 1);
		}
		{
			int closeX = barSeries.getLastIndex() * chartProperties.getBarWidth();
			double closeY = MathUtil.map(currentClosePrice, minLowPrice, maxHighPrice, maxY, minY);
			graphics.setColor(chartColorProperties.getLine());
			graphics.drawLine(closeX, (int) closeY, width, (int) closeY);
			String closeText = FormattingUtil.number(currentClosePrice, 8);
			graphics.setColor(chartColorProperties.getText());
			graphics.drawString(closeText, width - metrics.stringWidth(closeText), (int) closeY - 1);
		}

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(chartProperties.getDateTimePattern());

		graphics.setColor(chartColorProperties.getText());
		graphics.translate(2, metrics.getAscent());
		graphics.drawString(String.format("Pair: %s / %s", symbol.getBaseAsset(), symbol.getQuoteAsset()), 0, 0);
		graphics.translate(0, metrics.getHeight());
		graphics.drawString(String.format("Interval: %s", chartProperties.getInterval()), 0, 0);
		graphics.translate(0, metrics.getHeight());
		graphics.drawString(String.format("From: %s", dateTimeFormatter.format(barSeries.getFirst().getOpenTime())), 0, 0);
		graphics.translate(0, metrics.getHeight());
		graphics.drawString(String.format("To: %s", dateTimeFormatter.format(barSeries.getLast().getOpenTime())), 0, 0);

		log.info("Chart image rendering took {} ms", timeTracker.finish().getDurationMillis());
		return image;
	}

}
