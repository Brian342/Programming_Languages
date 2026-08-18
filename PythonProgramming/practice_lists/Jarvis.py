import os
import time
import pyaudio
import playsound
from gtts import gTTS
import openai
import speech_recognition as sr

api_key = "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"
lang = 'en'

openai.api_key = api_key

def get_audio():
    r = sr.Recognizer()
    with sr.Microphone(device_index=0) as source:
        print("Listening...")
        audio = r.listen(source)
        said = ""

        try:
            print("Recognizing...")
            said = r.recognize_google(audio)
            print("You said:", said)

            if "Friday" in said:
                completion = openai.ChatCompletion.create(model="gpt-3.5-turbo", messages=[{"role": "user", "content": said}])
                text = completion.choices[0].message.content
                speech = gTTS(text=text, lang=lang, slow=False, tld="com.au")
                speech.save("response.mp3")
                playsound.playsound("response.mp3")

        except sr.UnknownValueError:
            print("Google Speech Recognition could not understand audio")
        except sr.RequestError as e:
            print("Could not request results from Google Speech Recognition service; {0}".format(e))
        except Exception as e:
            print("Error:", str(e))

while True:
    get_audio()
