from Categories.useFaiss import FaissCategorization
from Categories.test.data import data

from Description.summarizeSpend import SummarizeSpend
from Description.contentSearch import ContentSearch
from Description.makeCardName import MakeCardName

from CreditCard.recomandCreditCard import RecommandCreditCard
from CreditCard.creditCardSearch import CreditCardSearch

import os

os.environ['OPENAI_API_KEY'] = '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'

result = FaissCategorization(data)
print(result)
result2 = SummarizeSpend(result)
print(result2)
result3 = ContentSearch(result2)
name = MakeCardName(result3)
print("Name : "+name)
print(result3)
result4 = RecommandCreditCard(result3)
print(result4)
result5 = CreditCardSearch(result4)
print(result5)