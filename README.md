# Kafka Consumer
  
  **Steps to follow**
  
  **Create DB tables**
  Create tables using the script available under resource folder(resources/templates/createTableScripts.txt).
  
1. Start **Zookeeper** using **bin/zookeeper-server-start.sh config/zookeeper.properties**.
2. Start **Kafka** server using **bin/kafka-server-start.sh config/server.properties**.
3. Create Kafka topic using **bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic            kafka_dribble_topic**.
4. Start the server to listen to **kafka_dribble_topic**.

