# デモプロジェクト

Apache PulsarとApache Flinkを使用したデモプロジェクトです。

## 前提条件

- Java 11以上
- Maven 3.6以上
- Docker および Docker Compose

## 始め方

### プロジェクトのビルド

```bash
mvn clean package
```

### Dockerサービスの実行

このプロジェクトには、Apache PulsarとApache FlinkのためのDocker Compose設定が含まれています。

```bash
cd docker
docker compose up -d
```

サービスは以下で利用可能です：
- **Pulsar Broker**: `pulsar://localhost:6650`
- **Pulsar HTTP**: `http://localhost:8080`
- **Flink Dashboard**: `http://localhost:8081`

サービスを停止するには：

### Topicの確認

作成されたTopicを確認するには以下のコマンドを実行します：

```bash
docker exec pulsar bin/pulsar-admin topics list public/default
```

