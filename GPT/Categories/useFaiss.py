from langchain.embeddings import HuggingFaceEmbeddings
from langchain.vectorstores import FAISS
import os

def FaissCategorization(userSpendData):
    try:
        embeddings = HuggingFaceEmbeddings()
        # fdb = FAISS.load_local("./DB/faiss-category", embeddings)
        fdb = FAISS.load_local(f"{os.path.dirname(os.path.realpath(__file__))}/DB/faiss-category", embeddings)
        categoryList = ["교육", "교통", "기타", "문화/여가", "반려동물", "배달 음식", "생활", "식비", "여행/숙박", "온라인 쇼핑", "운동", "의료/건강", "주거/통신", "카페/간식", "편의점/마트"]
        addCategory = []
        for spend in userSpendData:
            docs = fdb.similarity_search(spend['dealPlaceName'], k=1)
            spend['category'] = docs[0].metadata['category']
            addCategory.append(spend)
        categorize = {}
        for cat in categoryList:
            if categorize.get(cat) is None:
                categorize[cat] = []
        for data in addCategory:
            categorize[data['category']].append({"amount" : data['amount']})
        result = []
        for cat in categorize:
            temp = {}
            temp['keyword'] = cat
            totalAmount = 0
            frequency = len(categorize[cat])
            for c in categorize[cat]:
                totalAmount += int(c['amount'])
            temp['totalAmount'] = totalAmount
            temp['frequency'] = frequency
            if temp.get('frequency') != 0:
                result.append(temp)
        return result
    except Exception as e:
        print(e)

# from .test.data import data
# print(FaissCategorization(data))

#벡터 DB로 카테고리화
# from langchain.prompts import PromptTemplate
# from langchain.chains import LLMChain
# prompt = PromptTemplate(
#         input_variables=["Data", "vectorDB"],
#     template="""
#     You are the helpful agent that analyzes payment history data throught user's input. Based on the analyzed data, you define the categorization in JSON.
#     - do not add any description about output, only output Result.

#     Categorize based on the names in user's input
#     - Use this data to categorize it {vectorDB}
    
#     out should be only JSON object with the contain category_name, id, name

#     conversation:
#     user input : {Data}
#     Result : 
#     """
# )
# chain = LLMChain(llm=chat, prompt=prompt, verbose=True)
# result1 = chain.run(Data=data, vectorDB=embeddingList)
# print(result1)