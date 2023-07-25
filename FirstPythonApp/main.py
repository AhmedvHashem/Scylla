import requests

if __name__ == "__main__":
    response = requests.get("https://httpbin.org/ip")
    print("Your IP is {0}".format(response.json()["origin"]))
