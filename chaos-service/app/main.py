import time
import random
from fastapi import FastAPI, HTTPException
from app.config import ChaosConfig

app = FastAPI(title="Chaos Service")


@app.get("/chaos/ping")
def ping():

    # simulate downtime (service completely unavailable)
    if ChaosConfig.DOWNTIME_MODE:
        raise HTTPException(status_code=503, detail="Service down")

    # simulate latency
    if ChaosConfig.LATENCY_MS > 0:
        time.sleep(ChaosConfig.LATENCY_MS / 1000)

    # simulate random failures
    if ChaosConfig.ERROR_RATE_PERCENT > 0:
        roll = random.randint(1, 100)
        if roll <= ChaosConfig.ERROR_RATE_PERCENT:
            raise HTTPException(status_code=500, detail="Injected failure")

    return {"message": "chaos service alive"}
