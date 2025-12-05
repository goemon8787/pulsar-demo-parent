#!/bin/bash

# Kafkaユーザー向けの解説:
# PulsarのTopicは階層構造を持っています: {persistence}://{tenant}/{namespace}/{topic}
# - persistence: persistent (永続化) または non-persistent (非永続化)
# - tenant: マルチテナント機能のルート (デフォルトは public)
# - namespace: トピックをグループ化する名前空間 (デフォルトは default)
# - topic: トピック名
#
# Kafkaの "Topic" は Pulsarの "Topic" に相当しますが、Pulsarはテナントと名前空間でより細かく管理できます。

TOPIC_NAME="persistent://public/default/my-topic"
CONTAINER_NAME="pulsar"

echo "=== Pulsar Topic作成 & データ投入スクリプト ==="

# 1. Topicの作成
# Kafkaでは auto.create.topics.enable=true で自動作成されますが、Pulsarもデフォルトで自動作成されます。
# ここでは明示的に作成を確認します（実際にはproduce時に自動作成されることが多いです）。
echo "Creating topic: $TOPIC_NAME"
docker exec $CONTAINER_NAME bin/pulsar-admin topics create $TOPIC_NAME
if [ $? -eq 0 ]; then
    echo "Topic created successfully."
else
    echo "Topic creation failed or already exists."
fi

# 2. サンプルデータの投入
# Kafkaの kafka-console-producer に相当するのが pulsar-client produce です。
echo "Producing sample messages..."
docker exec $CONTAINER_NAME bin/pulsar-client produce $TOPIC_NAME \
    --messages "Hello Pulsar 1,Hello Pulsar 2,Hello Pulsar 3" \
    --num-produce 3

echo "=== 完了 ==="
echo "確認コマンド:"
echo "docker exec -it $CONTAINER_NAME bin/pulsar-client consume $TOPIC_NAME -s my-subscription -n 0"
