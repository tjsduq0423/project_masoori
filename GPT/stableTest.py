from Categories.useFaiss import FaissCategorization
from Categories.test.data import data

import os
os.environ['OPENAI_API_KEY'] = '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'

from StableDiffusionPrompt.promptWriting import PromptWriting
from StableDiffusionPrompt.makePNG import MakePng

result = FaissCategorization(data)
sorted_result = sorted(result, key=lambda x: x['totalAmount'], reverse=True)
keyword = sorted_result[0]['keyword']
result4 = PromptWriting(keyword)
prompt = result4.replace("프롬프트 : ", "")
print(prompt)
image = MakePng("test@gmail.com", "20231005", prompt)
print(image)
