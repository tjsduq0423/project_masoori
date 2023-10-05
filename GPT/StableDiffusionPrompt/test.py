import json
import requests
import io
import base64
# from PIL import Image, PngImagePlugin

# url = "https://c26ac38078231d0cbf.gradio.live"
# url = "http://149.28.51.188/api"
url = "http://sonagi.site/api"

user = "test@gmail.com"
time = "202309271515"
test = user+"_"+time

payload = {
    # "prompt": "masterpiece, best quality, Two dogs howling at the celestial orb while a crawfish emerges from the water's edge, Distant towers stand tall, and sparks ignite the space between them",
    "prompt": "best_quality, boy, eating pizza, harry poter style",
    "negative_prompt": "lowres, bad anatomy, bad hands, text, error, missing fingers, extra digit, fewer digits, cropped, worst quality, low quality, normal quality, jpeg artifacts, signature, watermark, username, blurry, artist name, hand",
    "steps": 40,
    "override_settings": {
        # "sd_model_checkpoint": "AnythingV5Ink_v5PrtRE.safetensors [7f96a1a9ca]"
        "sd_model_checkpoint": "AnythingV5Ink_ink.safetensors [a1535d0a42]"
    },
    "override_settings_restore_afterwards": True,
    "sampler_index": "DPM++ 2M Karras",
    # "sampler_index": "Euler",
    "cfg_scale": 7,
    "width": 720,
    "height": 1280,
    # "width": 1080,
    # "height": 1920,
    "save_images": True,
    "styles": [test]
}

response = requests.post(url=f'{url}/sdapi/v1/txt2img', json=payload)
print(response)
print(response.json())

# r = response.json()
# print(r)

# for i in r['images']:
#     image = Image.open(io.BytesIO(base64.b64decode(i.split(",",1)[0])))

#     png_payload = {
#         "image": "data:image/png;base64," + i
#     }
#     # print("PayLoad : ", png_payload)

#     response2 = requests.post(url=f'{url}/sdapi/v1/png-info', json=png_payload)
#     print("response2",response2)

#     pnginfo = PngImagePlugin.PngInfo()
#     pnginfo.add_text("parameters", response2.json().get("info"))
#     image.save(f'./{user}_output1234.png', pnginfo=pnginfo)