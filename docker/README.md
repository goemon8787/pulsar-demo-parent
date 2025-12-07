# Pulsar Docker 環境

ローカル開発用のPulsar Standalone環境です。

## 起動

```bash
docker-compose up -d
```

Pulsarサービスは `pulsar://localhost:6650` で利用可能になります。
管理UIなどは含まれていません。

## 停止

```bash
docker-compose down
```

## 初期化スクリプト

`init-pulsar.sh` はPulsarコンテナ起動時に自動的に実行され、必要なテナントやネームスペースを作成します。
(必要に応じて修正してください)
