import base64
import json
import io
import os
from PIL import Image
from ultralytics import YOLO
from flask_restful import Resource
from flask import request,make_response
class MaskDetection(Resource):
    def __init__(self):
        #학습된 모델 로드
        self.model = YOLO('best.pt')

    def post(self):
        base64Encoded = request.form['base64Encoded']
        image_b64 = base64.b64decode(base64Encoded)
        image_memory = Image.open(io.BytesIO(image_b64))
        image_memory.save('./images/new.jpg')
        results = self.model.predict(['./images/new.jpg'],save=True)
        print('results\n',results)
        print('results[0].save_dir\n', results[0].save_dir)
        with open(os.path.join(results[0].save_dir, 'new.jpg'),'rb') as f:
            base64Predicted= base64.b64encode(f.read()).decode('utf-8')
        print('base64Predicted\n',base64Predicted)
        return make_response(json.dumps({'base64':base64Predicted},ensure_ascii=False))

