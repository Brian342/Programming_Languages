from flask import Flask, render_template
from flask_bootstrap import Bootstrap
from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField
from wtforms.validators import DataRequired

app = Flask(__name__)
app.config['SECRET_KEY'] = 'hard to guess string'
Bootstrap = Bootstrap(app)


class nameForm(FlaskForm):
    name = StringField('What is your Name?', validators=[DataRequired])
    submit = SubmitField('Submit')


if __name__ == "__main__":
    app.run(debug=True)
