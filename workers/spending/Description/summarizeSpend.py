from langchain.chat_models import ChatOpenAI
from langchain.prompts import PromptTemplate
from langchain.chains import LLMChain

def SummarizeSpend(categorizedSpend):
    try:
        chat = ChatOpenAI(model='gpt-3.5-turbo', temperature=0.7, verbose=True, max_tokens=2048, max_retries=3)
        prompt = PromptTemplate(
            input_variables=["Data"],
            template="""
            You enter the witch's study where you analyze consumption. 
            - At the beginning of the answer, the witch prints out a sentence that seems to welcome you. The witch says: "이번주에는 어떤 일이신가요?".
            - At the end of the answer, the witch prints out a sentence that seems to returns you. The witch says:"즐거운 시간이었습니다. 다음주에도 볼 수 있으면 좋겠네요.".
            - All outputs are Korean only.
            - All outputs get rid of double quotes.

            Analyzing three consumption
            - "주로 돈을 사용한 곳을 확인해 볼까요?"
            - "가장 자주 결제한 카테고리 : keyword(frequency), keyword(frequency), keyword(frequency)"
            - "가장 많이 결제한 카테고리 : keyword(totalAmount), keyword(totalAmount), keyword(totalAmount)"

            Please Recommend two ways to save money through the characteristics of your consumption.
            - "소비를 줄이고 싶으시다면 아래와 같은 방법이 있습니다."
            - The recommended content is expressed as "방법 1: ",  "방법 2: "

            Summarize the consumption characteristics of the user in one sentence.
            - "당신의 소비는 한 문장으로 요약하면 " Write down the summary after this sentence.

            conversation:
            user input : {Data}
            Result : 
            """
        )
        chain = LLMChain(llm=chat, prompt=prompt, verbose=True)
        result = chain.run(Data=categorizedSpend)
        result = result.replace("\"", "")
        return result
    except Exception as e:
        print(e)
# print(SummarizeSpend())