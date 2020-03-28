docker rmi registry.cn-beijing.aliyuncs.com/yangkaifei/order-api:1.0.0
docker rmi order-api:1.0.0
mvn clean install
## 打包
mvn clean package -Dmaven.test.skip=true -U
## 构建docker镜像
docker build -t order-api:1.0.0 -f order-api/src/main/docker/Dockerfile .
#docker login --username=13730404063 registry.cn-beijing.aliyuncs.com
#登录镜像
docker tag $(docker images |grep "order-api" | awk '{print $3}') registry.cn-beijing.aliyuncs.com/yangkaifei/order-api:1.0.0
docker push registry.cn-beijing.aliyuncs.com/yangkaifei/order-api:1.0.0

echo "====================goods-c================================"
docker rmi registry.cn-beijing.aliyuncs.com/yangkaifei/order-admin:1.0.0
docker rmi order-admin:1.0.0
#mvn clean install
### 打包
#mvn clean package -Dmaven.test.skip=true -U
## 构建docker镜像
docker build -t order-admin:1.0.0 -f order-admin/src/main/docker/Dockerfile .
docker tag $(docker images |grep "order-admin" | awk '{print $3}') registry.cn-beijing.aliyuncs.com/yangkaifei/order-admin:1.0.0
docker push registry.cn-beijing.aliyuncs.com/yangkaifei/order-admin:1.0.0
