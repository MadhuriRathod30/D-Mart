#!/usr/bin/env bash

#-----------------------------------
# STEP 1 - Clean up the ports
#-----------------------------------

echo "🔪 Killing processes on ports 8080 to 8084..."
for port in {8080..8084}
do
  pid=$(netstat -aon | grep ":$port" | awk '{print $NF}' | head -n 1)
  if [ -n "$pid" ]; then
    echo "🔪 Killing PID $pid on port $port"
    taskkill //PID $pid //F
  else
    echo "✅ No process running on port $port"
  fi
done

#-------------------------------------
#STEP 2 - Start the process
#--------------------------------------

#function to run service
echo "service run begin"
run_service(){
  local dir=$1
  local name=$2


  echo "Starting $name $dir..."


  start "$name" winpty bash -c "cd $dir && ./mvnw spring-boot:run; exec bash"
}

echo "Launching all the services"
  # Adjust paths below based on your folder structure
  run_service "./OrderService" "Order Service"
  run_service "./AssociateService" "Associate Service"
  run_service "./PackingService" "Packing Service"
  run_service "./PickingService" "Picking Service"
  run_service "./APIGW" "API Gateway"

  echo "✅ All services are launching... check terminals."



