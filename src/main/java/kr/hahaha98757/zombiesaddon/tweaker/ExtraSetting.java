package kr.hahaha98757.zombiesaddon.tweaker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ExtraSetting {

	public static void writeFile() {
		try {
			Path filePath = Paths.get("config/ExtraSettings.txt");

			List<String> content = Arrays.asList("#Turn off the wave delays of SST.", "true",
					"#Turn off the timer of Zombies Utils.", "true");

			Files.write(filePath, content, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readFile(int i) {
		try {
			Path filePath = Paths.get("config/ExtraSettings.txt");

			List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

			String str = lines.get(i);

			return str;
		} catch (IOException e) {
			writeFile();
			return null;
		}
	}

}
