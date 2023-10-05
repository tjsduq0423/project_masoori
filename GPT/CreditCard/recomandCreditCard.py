from langchain.embeddings import HuggingFaceEmbeddings
from langchain.indexes.vectorstore import VectorStoreIndexWrapper
from langchain.vectorstores import FAISS
from langchain.chat_models import ChatOpenAI
import os

def RecommandCreditCard(spendSummary):
    try:
        embeddings = HuggingFaceEmbeddings()
        chat = ChatOpenAI(model='gpt-3.5-turbo', temperature=0.5, verbose=True, max_tokens=2048)
        fdb = FAISS.load_local(f"{os.path.dirname(os.path.realpath(__file__))}/DB/faiss-creditcard", embeddings)
        index = VectorStoreIndexWrapper(vectorstore=fdb)
        result = index.query(f"{spendSummary['frequencyKeyword']}에 {spendSummary['frequency']} 소비를 하고, {spendSummary['amountKeyword']}에 {spendSummary['amount']}원 소비를 하는 사용자를 위한 카드 6장을 추천해줘. 출력 양식은 이렇게 해줘 '카드ID : \n 카드명 : \n 혜택 : \n 이유 :  \n' 이유는 최대한 상세하게 작성해줘.", llm=chat, verbose=True)
        return result
    except Exception as e:
        print(e)