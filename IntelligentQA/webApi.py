import json

import judge
from flask import Flask, jsonify, request

app = Flask(__name__)


@app.route('/getQuestionDetails', methods=['post'])
def getQuestionDetails():
    recv_data = request.get_data()
    print(recv_data)
    if recv_data:
        json_re = json.loads(recv_data)
    else:
        print("receive data is empty")

    question = json_re["question"]
    return jsonify(judge.predict(question))


app.run(host='0.0.0.0', port=8802, debug=True)
