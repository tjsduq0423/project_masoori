from langchain.chat_models import ChatOpenAI
from langchain.prompts import PromptTemplate
from langchain.chains import LLMChain

def MakeCardName(spendData):
    try:
        chat = ChatOpenAI(model='gpt-3.5-turbo', temperature=0.3, verbose=True, max_tokens=2048, max_retries=3)
        prompt = PromptTemplate(
            input_variables=["Data"],
            template="""
            You're a nomenclist.

            Please recommend a name that doesn't exceed 15 characters in English for user's input of "amountKeyword"

            conversation:
            user input : {Data}
            Result : 
            """
        )
        chain = LLMChain(llm=chat, prompt=prompt, verbose=True)
        result = chain.run(Data=spendData)
        return result
    except Exception as e:
        print(e)