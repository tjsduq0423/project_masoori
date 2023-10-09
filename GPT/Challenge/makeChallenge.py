from langchain.chat_models import ChatOpenAI
from langchain.prompts import PromptTemplate
from langchain.chains import LLMChain

def MakeChallenge(categorizedSpend):
    try:
        chat = ChatOpenAI(model='gpt-3.5-turbo', temperature=0.3, verbose=True, max_tokens=2048, max_retries=3)
        prompt = PromptTemplate(
            input_variables=["Data"],
            template="""
            You are an AI that creates a challenge through consumption input by the user.

            Based on the data entered by the user, a challenge is created by selecting either the amount or the frequency
            - if it is amount "amountKeyword 소비 money원 줄이기" in the money seat, choose from 10, 20, 30, 40 and divide the amount by the selected number
            - if it is frequency 
            conversation:
            user input : {Data}
            Result : 
            """
        )
        chain = LLMChain(llm=chat, prompt=prompt, verbose=True)
        result = chain.run(Data=categorizedSpend)
        return result
    except Exception as e:
        print(e)