# LogAnomalyDetector

🚨 **Detecção Básica de Anomalias em Logs com Java** 🚨

---

## Sobre

Este projeto contém um script simples em Java para **detecção básica de anomalias em arquivos de log**, uma técnica essencial para profissionais que atuam com segurança da informação e análise de incidentes.

Logs são fontes riquíssimas de informações sobre acessos, falhas e eventos suspeitos em sistemas. Detectar padrões anômalos nesses registros ajuda a identificar ataques e comportamentos fora do comum.

---

## Funcionalidades

- Leitura de arquivos de log no formato texto.
- Contagem da frequência de eventos detectados no log.
- Identificação e alerta de eventos que ultrapassam um limite de ocorrência (threshold configurável).
- Código simples e facilmente adaptável para diferentes formatos de log.

---

## Como funciona?

1. O programa lê o arquivo de log linha por linha.
2. Extrai um evento simplificado de cada linha (a palavra que aparece após o timestamp).
3. Conta quantas vezes cada evento ocorre.
4. Apresenta os eventos cuja ocorrência ultrapassa um limite definido (padrão: 5).

---

## Uso

### Pré-requisitos

- Java JDK instalado (versão 8 ou superior).

### Passos

1. Clone este repositório ou baixe o arquivo `LogAnomalyDetector.java`.
2. Compile o código:
   ```bash
   javac LogAnomalyDetector.java
   ```
3. Execute o programa, passando o arquivo de log como argumento:
   ```bash
   java LogAnomalyDetector caminho/para/seu_arquivo_log.txt
   ```

---

## Exemplo de saída

```
Eventos detectados com frequência anormal:
- Evento: 'ERROR' | Ocorrências: 12
- Evento: 'WARN' | Ocorrências: 7
```

---

## Possíveis melhorias

- Ajustar a função `extractEvent` para diferentes formatos de logs, usando expressões regulares.
- Implementar detecção de anomalias mais avançada usando técnicas estatísticas ou machine learning.
- Adicionar integração com sistemas de alerta (e-mail, webhook, etc).
- Criar interface gráfica para facilitar uso.

---

## Código

```java
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
        String[] parts = logLine.split(" ");
        return parts.length > 1 ? parts[1] : "unknown";
    }
}
```

---

## Licença

Este projeto está sob a licença MIT — sinta-se à vontade para usar e modificar conforme suas necessidades.

---

## Contato

Qualquer dúvida, sugestão ou colaboração, fique à vontade para abrir uma issue ou pull request!

---

Obrigado por visitar! 🚀

---

---

**Se curtiu, deixa um ⭐ e compartilha com a galera da segurança da informação!**
