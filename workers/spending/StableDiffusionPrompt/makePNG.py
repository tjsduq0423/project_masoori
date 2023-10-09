import requests
def MakePng(user, time, prompt):
    try:
        url = "http://sonagi.site/api"
        fileName = f"{user}_{time}"
        payload = {
            "prompt": f"best_quality, {prompt}",
            "negative_prompt": "nsfw, simple background, lowres, bad hands, text, error, missing fingers, fewer fingers, strange fingers, extra digit, fewer digits, cropped, worst quality, signature, watermark, username, blurry,bad anatomy,jpeg artifacts, ugly, pregnant,vore,duplicate,mutilated,missing legs,missing arms,extra arms,pubic hair,plump,bad legs,error legs,bad feet,mutation,transexual,bad proportions,nipples,glans,bare thighs,naked,disfigured,bad art,deformed,extra limbs,long neck,cross-eye,moles",
            "steps": 40,
            "override_settings": {
                "sd_model_checkpoint": "AnythingV5Ink_ink.safetensors [a1535d0a42]"
            },
            "override_settings_restore_afterwards": True,
            "sampler_index": "DPM++ 2M Karras",
            "cfg_scale": 7,
            "width": 720,
            "height": 1280,
            # "width": 1080,
            # "height": 1920,
            "save_images": True,
            "styles": [fileName]
        }
        response = requests.post(url=f'{url}/sdapi/v1/txt2img', json=payload)
        if response.status_code != 200:
            raise ValueError("사진 생성 실패")
        return response.json()["parameters"]['styles'][0]
    except Exception as e:
        print(e)