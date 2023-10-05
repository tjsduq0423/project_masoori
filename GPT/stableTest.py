from Categories.useFaiss import FaissCategorization
from Categories.test.data import data

import os

os.environ['OPENAI_API_KEY'] = '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'

from Description.summarizeSpend import SummarizeSpend
from Description.contentSearch import ContentSearch

from StableDiffusionPrompt.promptWriting import PromptWriting

result = FaissCategorization(data)
# result2 = SummarizeSpend(result)
# print(result2)
# result3 = ContentSearch(result2)
# print(result3)
result4 = PromptWriting(result)
print(result4)