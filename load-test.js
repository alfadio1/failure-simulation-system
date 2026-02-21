import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  vus: 20,           // simulating 20 virtual users
  duration: '30s',   // run for 30 seconds
};

export default function () {
  http.get('http://localhost:8086/api/demo');
  sleep(1);
}