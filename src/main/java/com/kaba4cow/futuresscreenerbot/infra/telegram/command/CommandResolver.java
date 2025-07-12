package com.kaba4cow.futuresscreenerbot.infra.telegram.command;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommandResolver {

	private final Map<String, Command> commandToIdentifierMappings = new TreeMap<>();

	private final Map<Command, String> identifierToCommandMappings = new TreeMap<>();

	@SneakyThrows
	@PostConstruct
	public void loadCommands() {
		log.info("Loading commands");
		URL resource = getClass().getClassLoader().getResource("commands.json");
		Path path = Paths.get(resource.toURI());
		JSONObject json = new JSONObject(Files.readString(path));
		for (String key : json.keySet()) {
			Command identifier = Command.valueOf(key);
			String command = json.getString(key);
			commandToIdentifierMappings.put(command, identifier);
			identifierToCommandMappings.put(identifier, command);
			log.info("Registered command mapping \"{}\" <-> {}", command, identifier);
		}
	}

	public Command resolveCommand(String command) {
		if (commandToIdentifierMappings.containsKey(command))
			return commandToIdentifierMappings.get(command);
		else
			return Command.UNKNOWN;
	}

	public boolean hasCommand(String command) {
		return commandToIdentifierMappings.containsKey(command);
	}

	public String getCommand(Command identifier) {
		return identifierToCommandMappings.get(identifier);
	}

}
