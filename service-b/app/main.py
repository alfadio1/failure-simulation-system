from fastapi import FastAPI
import requests
import os

from fastapi import HTTPException



app = FastAPI()

CHAOS_URL = os.getenv("CHAOS_URL", "http://chaos-service:8001")

@app.get("/process")
def process_request():
    try:
        response = requests.get(f"{CHAOS_URL}/chaos/ping", timeout=2)
        response.raise_for_status()
        return {"status": "success from service-b"}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
    # except Exception as e:
    #     return {"status": "failure from service-b", "error": str(e)}