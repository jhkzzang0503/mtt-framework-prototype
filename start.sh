#!/bin/bash

FRONTEND_DIR="frontend"


PID_FILE_FRONTEND_ADMIN="/tmp/frontend-admin.pid"
PID_FILE_FRONTEND_WEB="/tmp/frontend-web.pid"

echo "Starting all servers..."

echo "Starting frontend servers..."
cd "$FRONTEND_DIR"

npm run build:admin > /dev/null 2>&1
npm run admin > /dev/null 2>&1 &
echo $! > "$PID_FILE_FRONTEND_ADMIN"

npm run build:web > /dev/null 2>&1
npm run web > /dev/null 2>&1 &
echo $! > "$PID_FILE_FRONTEND_WEB"

cd ..

echo "Starting backend server..."
./mvnw install && ./mvnw spring-boot:run
