import java.io.*;
import java.util.*;

public class LogAnomalyDetector {

    private static final int THRESHOLD = 5; // Número mínimo de ocorrências para gerar alerta

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Uso: java LogAnomalyDetector <arquivo_log>");
            return;
        }

        String logFile = args[0];
        Map<String, Integer> eventCount = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String event = extractEvent(line);
                eventCount.put(event, eventCount.getOrDefault(event, 0) + 1);
            }
        }

        System.out.println("Eventos detectados com frequência anormal:");
        for (Map.Entry<String, Integer> entry : eventCount.entrySet()) {
            if (entry.getValue() > THRESHOLD) {
                System.out.printf("- Evento: '%s' | Ocorrências: %d%n", entry.getKey(), entry.getValue());
            }
        }
    }

    private static String extractEvent(String logLine) {
        // Supondo que o evento seja a palavra após o timestamp
        String[] parts = logLine.split(" ");
        return parts.length > 1 ? parts[1] : "unknown";
    }
}
