from flask_restful import Resource
from flask import make_response
from model.naver_news_crawling import naver_news_it
import json
import csv

class Naver(Resource):
    def get(self):
        articles=naver_news_it()
        news_dict=[]
        for titles,imageUrl,summary,_ in articles:
            title,link = titles
            news_dict.append({'title':title,'link':link,'imageUrl':imageUrl,'summary':summary})

        #csv파일
        with open('naver.csv', 'w', encoding='utf8',newline='') as f:
            writer= csv.DictWriter(f,fieldnames=['title','link','imageUrl','summary'])
            writer.writeheader()
            writer.writerows(news_dict)

        return make_response(json.dumps(news_dict,ensure_ascii=False))