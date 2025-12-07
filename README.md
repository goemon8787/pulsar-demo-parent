# Pulsar Producer/Consumer Demo

Apache Pulsarを使用したProducerとConsumerのデモプロジェクトです。
Mavenマルチモジュールプロジェクトとして構成されており、実行用スクリプトを含む配布用ZIPファイルを生成します。

## プロジェクト構成

```
pulsar-demo-parent/
├── docker/                 # Pulsar起動用Dockerコンポーズファイル
├── pulsar-demo/            # ソースコードルート
│   ├── pulsar-demo-modules/
│   │   ├── pulsar-producer/ # Producerモジュール
│   │   └── pulsar-consumer/ # Consumerモジュール
│   └── pom.xml             # 親POM
└── README.md               # このファイル
```

## 前提条件

- Java 17+
- Maven 3.6+
- Docker & Docker Compose

## 実行フロー

### 1. Pulsarの起動

まずはローカルでPulsarブローカーを起動します。

```bash
cd docker
docker-compose up -d
```

詳細は [docker/README.md](docker/README.md) を参照してください。

### 2. ビルド

プロジェクトのルートディレクトリ(`pulsar-demo`)でビルドを実行します。

```bash
cd pulsar-demo
mvn clean package
```

### 3. Producer/Consumerの実行

ビルドが成功すると、各モジュールの `target` ディレクトリに実行用ZIPファイルが生成されます。

- **Producer**: `pulsar-demo-modules/pulsar-producer/target/pulsar-producer-1.0.0-SNAPSHOT-bin.zip`
- **Consumer**: `pulsar-demo-modules/pulsar-consumer/target/pulsar-consumer-1.0.0-SNAPSHOT-bin.zip`

ZIPファイルを解凍し、中のスクリプトを実行してください。
接続先やトピック名は `conf/config.properties` で設定可能です。

詳細な実行方法は各モジュールのREADMEを参照してください。
- [Producer README](pulsar-demo/pulsar-demo-modules/pulsar-producer/README.md)
- [Consumer README](pulsar-demo/pulsar-demo-modules/pulsar-consumer/README.md)
