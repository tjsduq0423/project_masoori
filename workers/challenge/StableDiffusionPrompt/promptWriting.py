from langchain.chat_models import ChatOpenAI
from langchain.prompts import PromptTemplate
from langchain.chains import LLMChain

            # You are an expert in writing the Stable Diffusion prompt. Based on user's input.
            # - do not add any description about output, only output Result.
            # The result is composed of a short sentence, and make a phrase without the subject verb.
def PromptWriting(spend):
    try:
        chat = ChatOpenAI(model='gpt-3.5-turbo', temperature=1, verbose=True, max_tokens=2048, max_retries=3)
        prompt = PromptTemplate(
            input_variables=["Data"],
            template="""
            I want you to help me make prompts for the Stable Diffusion.
            Stable Diffusion is a text-based image generation model that can create diverse and high-quality images based on users' requests. In order to get the best results from Stable diffusion, you need to follow some guidelines when composing prompts.
            - only output "프롬프트 : ".

            Here are some tips for writing prompts for Stable Diffusion:

                1. Be as specific as possible in the requests. Stable diffusion handles concrete prompts better than abstract or ambiguous ones. For example, instead of “portrait of a woman,” it is better to write “portrait of a Korean woman with brown eyes and red hair in Renaissance style.”
                2. Specify specific art styles or materials. If you want to get an image in a certain style or with a certain texture, then specify this in the request. For example, instead of “landscape,” it is better to write “watercolor landscape with mountains and lake."
                3. Specify specific artists for reference. If you want to get an image similar to the work of some artist, then specify his name in the request. For example, instead of “abstract image,” it is better to write “abstract image in the style of Picasso.”
                4. Don't use any pronouns.
                5. Avoid using thesr words: in a, a, an, the, with, of, and, is, of, by

            Write a one prompt with a various descriptions of the keyword after "프롬프트 : " .
            - The result is composed of a sentence in english without the subject verb.
            - Maximum length of sentence is 40 words.

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