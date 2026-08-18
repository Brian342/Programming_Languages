from flask import Flask, request, make_response, redirect, abort

app = Flask(__name__)


@app.route('/')
def index():
    response = make_response('<h1>This document carries a cookie!</h1>')
    response.set_cookie('answer', '42')
    redirection = "http://www.example.com"
    return response, redirection


app.add_url_rule('/', 'index', index)  # the URL, the endpoint name, and the view function


# urls with username
@app.route('/user/<name>')
def user(name):
    return '<h1>Hello, {}!</h1>'.format(name)


@app.route('/user/<id>')
def get_user(id):
    # user = load_user(id)
    if not user:
        abort(404)
    return '<h1>Hello, {}!</h1>'.format(user.name)
