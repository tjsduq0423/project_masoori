import dataTwo
# from data import data
from langchain.chat_models import ChatOpenAI
from langchain.prompts import PromptTemplate
import os
from langchain.chains import LLMChain
import json

os.environ['OPENAI_API_KEY'] = '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'

chat = ChatOpenAI(model='gpt-3.5-turbo', temperature=0.3)

prompt = PromptTemplate(
    input_variables=["Data"],
    template="""
    You are the helpful agent that analyzes payment history data throught user's input. Based on the analyzed data, you define the categorization in JSON.
    - do not add any description about output, only output Result.

    Categorize based on the names in user's input
    - Please categorize it in the form of "음식", "쇼핑", "기타", "미용", "교통", "편의점", "운동", "해외결제", "문화", "빵집", "배달", "카페", "온라인 쇼핑"
    
    out should be only JSON object with the contain category_name, id, tr_date, content, tr_amount, name

    conversation:
    user input : {Data}
    Result : 
    """
)

chain = LLMChain(llm=chat, prompt=prompt)
# result = chain.run(Data=json.dumps(data))
# result1 = chain.run(Data=data)
result1 = chain.run(Data=dataTwo.data)

print(result1)
# print(full_output)
file = open('output1.txt', 'w', encoding='UTF-8')
file.write(json.dumps(result1))
file.close()

print("=====================================")

prompt = PromptTemplate(
    input_variables=["Data"],
    template="""
        You are the helpful agent that to bind the data throught user's input. Based on the analyzed data, you define the categorization in JSON.
        - do not add any description about output, only output Result.

        out should be only JSON object with the Group with the same category_name.
        - frequency for category, sum of tr_amounts of data contained in category totalAmount, and list with the same data as object list These are values

        conversation:
        user input : {Data}
        Result : 

    """
)

chain = LLMChain(llm=chat, prompt=prompt)
result = chain.run(Data=result1)

print(result)
file = open('output2.txt', 'w', encoding='UTF-8')
file.write(result)
file.close()