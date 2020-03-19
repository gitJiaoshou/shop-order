sudo docker stop order-api1.0.0 && sudo docker rm order-api1.0.0

sudo docker rmi $(sudo docker images -q)

sudo docker run --privileged=true --net=host \
           -e SHOP_NACOS_ADDRESS=${SHOP_NACOS_ADDRESS} \
           -e SHOP_NACOS_NAMESPACE=${SHOP_NACOS_NAMESPACE} \
           -e SHOP_NACOS_CLUSTER_NAME=${SHOP_NACOS_CLUSTER_NAME} \
           -e PROFILE_ACTIVE=${PROFILE_ACTIVE} \
           -e PUBLIC_IP=${PUBLIC_IP} \
           -v /srtc/logs:/srtc/logs \
           -d --name order-api1.0.0 \
           registry.cn-beijing.aliyuncs.com/yangkaifei/order-api:1.0.0
sudo docker logs -f order-api1.0.0

#=============================goods-c==========================\=========
sudo docker stop order-admin1.0.0 && sudo docker rm order-admin1.0.0

sudo docker rmi $(sudo docker images -q)

sudo docker run --privileged=true --net=host \
           -e SHOP_NACOS_ADDRESS=${SHOP_NACOS_ADDRESS} \
           -e SHOP_NACOS_NAMESPACE=${SHOP_NACOS_NAMESPACE} \
           -e SHOP_NACOS_CLUSTER_NAME=${SHOP_NACOS_CLUSTER_NAME} \
           -e PROFILE_ACTIVE=${PROFILE_ACTIVE} \
           -e PUBLIC_IP=${PUBLIC_IP} \
           -v /srtc/logs:/srtc/logs \
           -d --name order-admin1.0.0 \
           registry.cn-beijing.aliyuncs.com/yangkaifei/order-admin:1.0.0
sudo docker logs -f order-admin1.0.0
