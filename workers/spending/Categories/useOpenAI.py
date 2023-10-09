from langchain.chat_models import ChatOpenAI
from langchain.prompts import PromptTemplate
import os
from langchain.chains import LLMChain

os.environ['OPENAI_API_KEY'] = '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'

def OpenAICategorization(userSpendData):
    try:
        chat = ChatOpenAI(model='gpt-3.5-turbo', temperature=0.3, verbose=True, max_tokens=2048, max_retries=3)
        prompt = PromptTemplate(
            input_variables=["Data"],
            template="""
            You are the helpful agent that analyzes payment history data throught user's input. Based on the analyzed data, you define the categorization in JSON.
            - do not add any description about output, only output Result.

            Categorize based on the names in user's input
            - Please categorize it in the form of "교육", "교통", "기타", "문화/여가", "반려동물", "배달 음식", "생활", "식비", "여행/숙박", "온라인 쇼핑", "운동", "의료/건강", "주거/통신", "카페/간식", "편의점/마트"
            
            out should be only JSON object with the contain category_name, id, name

            conversation:
            user input : {Data}
            Result : 
            """
        )
        chain = LLMChain(llm=chat, prompt=prompt, verbose=True)
        result = chain.run(Data=userSpendData)
        return result
    except Exception as e:
        print(e)

# from .test.data import data
# print(OpenAICategorization(data))
# file = open('output1.json', 'w', encoding='UTF-8')
# file.write(json.dumps(result1))
# file.close()

# print("=====================================")

# prompt = PromptTemplate(
#     input_variables=["Data"],
#     template="""
#         You are the helpful agent that to bind the data throught user's input. Based on the analyzed data, you define the categorization in JSON.
#         - do not add any description about output, only output Result.

#         out should be only JSON object with the Group with the same category_name.
#         - frequency for category, sum of tr_amounts of data contained in category totalAmount, and list with the same data as object list These are values

#         conversation:
#         user input : {Data}
#         Result : 

#     """
# )

# chain = LLMChain(llm=chat, prompt=prompt)
# result = chain.run(Data=result1)

# print(result)
# file = open('output2.txt', 'w', encoding='UTF-8')
# file.write(result)
# file.close()