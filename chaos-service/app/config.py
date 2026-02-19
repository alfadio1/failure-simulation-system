import os


def env_int(name: str, default: int) -> int:
    raw = os.getenv(name, str(default))
    return int(raw)


def env_float(name: str, default: float) -> float:
    raw = os.getenv(name, str(default))
    return float(raw)


class ChaosConfig:
    """
    Simple env-based config.
    We'll keep it beginner-friendly today (no extra libs yet).
    """

    # percent of requests that should fail (0 to 100)
    ERROR_RATE_PERCENT: int = env_int("CHAOS_ERROR_RATE_PERCENT", 0)

    # fixed latency added to every request (milliseconds)
    LATENCY_MS: int = env_int("CHAOS_LATENCY_MS", 0)

    # extra random latency jitter (0..JITTER_MS) (milliseconds)
    JITTER_MS: int = env_int("CHAOS_JITTER_MS", 0)

    # if true, every request fails (simulates outage)
    DOWNTIME_MODE: bool = os.getenv("CHAOS_DOWNTIME_MODE", "false").lower() == "true"
