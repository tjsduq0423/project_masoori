import re

def CreditCardSearch(description):
    try:
        def is_convertible_to_int(value):
            try:
                int_value = int(value)
                return True
            except ValueError:
                return False

        creditCardList = []
        creditCard = {}
        for desc in description.split("\n"):
            cardId = re.search(r"카드ID([^\n]+)", desc)
            cardReason = re.search(r"이유([^\n]+)", desc)
            if cardId:
                if is_convertible_to_int(cardId.group(1).replace(":", "").replace(" ", "")):
                    creditCard['creditCardId'] = int(cardId.group(1).replace(":", "").replace(" ", ""))
            if cardReason:
                # print(cardReason)
                creditCard['reason'] = cardReason.group(1).replace(":", "")
            if creditCard.get('creditCardId') and creditCard.get('reason') :
                creditCardList.append(creditCard)
                creditCard = {}
        return creditCardList
    except Exception as e:
        print(e)