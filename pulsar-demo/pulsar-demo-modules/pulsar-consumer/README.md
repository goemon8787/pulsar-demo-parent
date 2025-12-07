# Pulsar Consumer

Pulsarからメッセージを受信するConsumerアプリケーションです。
指定されたトピックを購読し、受信したメッセージを標準出力に表示します。

## 設定

設定ファイルは `src/main/resources/configs/config.properties` にあります。
配布用ZIPを展開した後は `conf/config.properties` を編集することで設定変更可能です。

```properties
pulsar.serviceUrl=pulsar://localhost:6650
pulsar.topicName=my-topic
pulsar.subscriptionName=my-subscription
```

## ビルド

親プロジェクトのルートから実行してください。

```bash
mvn clean package
```

## 実行方法

1. 生成されたZIP (`target/pulsar-consumer-1.0.0-SNAPSHOT-bin.zip`) を解凍します。
2. ディレクトリに移動します。
3. 実行スクリプトを叩きます。

```bash
cd pulsar-consumer-1.0.0-SNAPSHOT
./bin/pulsar-consumer.sh
```

4. メッセージを受信するとコンソールに表示されます。 `Ctrl+C` で終了します。
