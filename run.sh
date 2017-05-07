echo "------------------------ CLOSING OLD SESSIONS -------------------------------"
screen -ls | grep Detached | grep aksesi | cut -d. -f1 | awk '{print $1}' | xargs kill

echo "------------------------ STARTING PROXY APPLICATION -----------------------------"
screen -d -m -S "aksesi-proxy-application" bash -c "cd ./api/ ; ./run.sh"

echo "------------------------ STARTING ENDPOINT -----------------------------"
screen -d -m -S "aksesi-endpoint-application" bash -c "cd ./aksesi-sample-endpoint/ ; ./run.sh"

screen -ls;
