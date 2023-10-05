from langchain.chat_models import ChatOpenAI
from langchain.prompts import PromptTemplate
from langchain.chains import LLMChain

            # You are an expert in writing the Stable Diffusion prompt. Based on user's input.
            # - do not add any description about output, only output Result.
            # The result is composed of a short sentence, and make a phrase without the subject verb.
def PromptWriting(spend):
    try:
        chat = ChatOpenAI(model='gpt-3.5-turbo', temperature=0.7, verbose=True, max_tokens=2048, max_retries=3)
        prompt = PromptTemplate(
            input_variables=["Data"],
            template="""
            You are an artificial intelligence that creates a prompt that generates images using artificial intelligence.
            - only output "프롬프트 : ".

            Select one keyword with the largest 'totalAmount'.
            - Write a prompt with a various descriptions of the keyword after "프롬프트 : " .
            
            The result is composed of a short sentence, and make a phrase without the subject verb.

            conversation:
            user input : {Data}
            Result : 
            """
        )
        chain = LLMChain(llm=chat, prompt=prompt, verbose=True)
        result = chain.run(Data=spend)
        return result
    except Exception as e:
        print(e)