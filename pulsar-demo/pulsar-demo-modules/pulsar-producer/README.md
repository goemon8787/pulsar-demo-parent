# Pulsar Producer

Pulsarにメッセージを送信するProducerアプリケーションです。
標準入力からメッセージを受け取り、指定されたトピックに送信します。

## 設定

設定ファイルは `src/main/resources/configs/config.properties` にあります。
配布用ZIPを展開した後は `conf/config.properties` を編集することで設定変更可能です。

```properties
pulsar.serviceUrl=pulsar://localhost:6650
pulsar.topicName=my-topic
```

## ビルド

親プロジェクトのルートから実行してください。

```bash
mvn clean package
```

## 実行方法

1. 生成されたZIP (`target/pulsar-producer-1.0.0-SNAPSHOT-bin.zip`) を解凍します。
2. ディレクトリに移動します。
3. 実行スクリプトを叩きます。

```bash
cd pulsar-producer-1.0.0-SNAPSHOT
./bin/pulsar-producer.sh
```

4. プロンプトが表示されたらメッセージを入力してEnterを押すと送信されます。`exit` で終了します。
