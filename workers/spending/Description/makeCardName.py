from langchain.chat_models import ChatOpenAI
from langchain.prompts import PromptTemplate
from langchain.chains import LLMChain
import re

import os
os.environ['OPENAI_API_KEY'] = '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'

def MakeCardName(spendData):
    try:
        chat = ChatOpenAI(model='gpt-3.5-turbo', temperature=0.7, verbose=True, max_tokens=2048, max_retries=3)
        prompt = PromptTemplate(
            input_variables=["Data"],
            template="""
            You're an artificial intelligence that recommends names.

            Please Create a fun name that doesn't exceed 15 characters in English for user's input of "amountKeyword"
            - Result only output "이름 : name".
            - Please remove the double quotation marks

            conversation:
            user input : {Data}
            Result : 
            """
        )
        chain = LLMChain(llm=chat, prompt=prompt, verbose=True)
        result = chain.run(Data=spendData)
        result = result.replace("이름 : ", "").replace("이름: ", "")
        return result
    except Exception as e:
        print(e)