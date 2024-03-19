from flask import Flask
from flask_restful import Api
from flask_cors import CORS
from api.naver_news import Naver

app = Flask(__name__)
api = Api(app)
CORS(app)

api.add_resource(Naver,'/naver')

if __name__ == '__main__':
    app.run(debug=True)