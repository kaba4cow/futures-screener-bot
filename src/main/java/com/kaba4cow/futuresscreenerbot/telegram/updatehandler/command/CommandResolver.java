package com.kaba4cow.futuresscreenerbot.telegram.updatehandler.command;

import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommandResolver {

	private final Map<String, CommandIdentifier> commandToIdentifierMappings = new TreeMap<>();
	private final Map<CommandIdentifier, String> identifierToCommandMappings = new TreeMap<>();

	@SneakyThrows
	@PostConstruct
	public void loadCommands() {
		log.info("Loading commands");
		ObjectMapper mapper = new ObjectMapper();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("commands.json")) {
			TypeReference<Map<String, String>> reference = new TypeReference<>() {};
			Map<String, String> raw = mapper.readValue(input, reference);
			for (Map.Entry<String, String> entry : raw.entrySet()) {
				CommandIdentifier identifier = CommandIdentifier.valueOf(entry.getKey());
				String command = entry.getValue();
				commandToIdentifierMappings.put(command, identifier);
				identifierToCommandMappings.put(identifier, command);
				log.info("Registered command mapping \"{}\" <-> {}", command, identifier);
			}
		}
	}

	public CommandIdentifier resolveIdentifier(String command) {
		if (commandToIdentifierMappings.containsKey(command))
			return commandToIdentifierMappings.get(command);
		else
			return null;
	}

	public String getCommand(CommandIdentifier identifier) {
		return identifierToCommandMappings.get(identifier);
	}

}
