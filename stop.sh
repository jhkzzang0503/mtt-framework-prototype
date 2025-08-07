#!/bin/bash

PID_FILE_FRONTEND_ADMIN="/tmp/frontend-admin.pid"
PID_FILE_FRONTEND_WEB="/tmp/frontend-web.pid"


stop_process() {
    local pid_file="$1"
    local service_name="$2"
    
    if [ -f "$pid_file" ]; then
        pid=$(cat "$pid_file")
        echo "Stopping $service_name server (PID: $pid)..."
        kill "$pid"
        rm "$pid_file"
        echo "$service_name server stopped."
    else
        echo "$service_name server is not running."
    fi
}

echo "Stopping all servers..."

stop_process "$PID_FILE_FRONTEND_ADMIN" "frontend admin"
stop_process "$PID_FILE_FRONTEND_WEB" "frontend web"

echo "All servers stopped."
