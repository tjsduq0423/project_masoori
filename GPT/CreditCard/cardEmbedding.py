from langchain.embeddings import HuggingFaceEmbeddings
from langchain.document_loaders import DirectoryLoader
from langchain.indexes import VectorstoreIndexCreator
from langchain.vectorstores import FAISS
import os

def CardEmbedding():
    try:
        embeddings = HuggingFaceEmbeddings()
        loader = DirectoryLoader(f"{os.path.dirname(os.path.realpath(__file__))}/CardList", glob="**/*.txt", show_progress=True)

        #카드 데이터 VectorDB화
        index = VectorstoreIndexCreator(
            vectorstore_cls=FAISS,
            embedding=embeddings,
            ).from_loaders([loader])
        index.vectorstore.save_local(f"{os.path.dirname(os.path.realpath(__file__))}/DB/faiss-creditcard")
        print("FAISS에 데이터 넣기")
    except Exception as e:
        print(e)