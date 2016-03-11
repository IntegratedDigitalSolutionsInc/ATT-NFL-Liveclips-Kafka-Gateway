# Kafka Gateway

The Kafka Gateway listens for incoming asset events via JMS messages and forwards them on to the appropriate Kafka topic. There are two asset messages that are handled: `asset_published` and `notify_asset`. The JMS source from which events are received and the Kafka topic on to which they are forwarded depends on the message type according to the following table:

| Message           | JMS source name | JMS source type | Kafka Topic     |
|-------------------|-----------------|-----------------|-----------------|
| `asset_published` | `assetStatus`   | topic           | `asset_publish` |
| `notify_asset`    | `assetCommands` | queue           | `asset_notify`  |
