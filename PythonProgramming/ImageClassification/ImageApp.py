import streamlit as st
import tensorflow as tf
import matplotlib.pyplot as plt
import numpy as np
import cv2

model = tf.keras.models.load_model("model.h5")

st.set_page_config(page_title="ImageApp", layout="wide")

CUSTOM_CSS = r"""
    <style>
:root[data-theme="light"] {
  --bg: #878789;
  --card: rgba(255,255,255,0.06);
  --text: #0b1220;
  --accent1: linear-gradient(90deg,#7c3aed, #06b6d4);
}
:root[data-theme="dark"] {
  --bg: #0b0b13;
  --card: rgba(255,255,255,0.04);
  --text: #dbeafe;
  --accent1: linear-gradient(90deg,#06b6d4, #7c3aed);
}

/* Apply glass card effect to streamlit elements */
main .block-container {
  background: linear-gradient(180deg, rgba(255,255,255,0.01), rgba(255,255,255,0.00));
  padding: 1.6rem 2rem;
}
section[data-testid="stSidebar"] .css-1d391kg {
  background: transparent;
}
.css-1d391kg, .css-1d391kg .stButton button {
  border-radius: 14px;
}

/* Title style */
.header {
  display:flex; align-items:center; gap:12px;
}
.logo-circle {
  width:56px;height:56px;border-radius:12px;
  background: var(--accent1);
  display:flex;align-items:center;justify-content:center;color:white;font-weight:700;
  box-shadow: 0 8px 30px rgba(99,102,241,0.15);
}

/* Card style used in columns */
.card {
  background: linear-gradient(180deg, rgba(255,255,255,0.02), rgba(255,255,255,0.01));
  border: 1px solid rgba(255,255,255,0.04);
  padding: 16px;
  border-radius: 12px;
}

/* small pills */
.pill {
  display:inline-block;padding:6px 10px;border-radius:999px;font-size:12px;background:rgba(255,255,255,0.03);
}

/* bot bubble */
.bot {
  background: linear-gradient(90deg, rgba(255,255,255,0.02), rgba(255,255,255,0.01));
  padding: 10px 12px;border-radius:12px;margin:6px 0;
}
</style>
"""

st.markdown(CUSTOM_CSS, unsafe_allow_html=True)

with st.sidebar:
    st.markdown(
        "<div style='display:flex;align-items:center;gap:10px'><div class='logo-circle'>IApp</div><div><h3 style='margin:0'>ImageApp</h3><div style='font-size:12px;color:gray'>TensorFlow · Sklearn · Explainability</div></div></div>",
        unsafe_allow_html=True)
    st.markdown("------")
    st.markdown("**Quick Guide**")
    with st.sidebar.expander("Image Guideline"):
        st.write(
            """
            - Maximum file size: 10MB
            - identify the name of Image
            - Supported formats: PNG, JPG, JPEG
            - Processing time depends on image size
            """
        )
    st.markdown("-------")
    uploaded = st.file_uploader("Upload an Image here", type=['png', 'jpeg', 'jpg'])
Class_name = [
    'airplane', 'automobile', 'bird',
    'cat', 'deer', 'dog', 'frog', 'horse',
    'ship', 'truck'
]

st.write("Know which Image you Uploaded")
st.markdown("--------")
st.write(
    "Try uploading an image to see the name of the Image. This code is open source and available [here](https://github.com/Brian342/ImageClassification) on GitHub"
)
st.markdown("---------")
st.info("Unload an image to continue")

col1, col2 = st.columns([2, 0.5])

with col1:
    st.write("uploaded Image :camera:")
with col2:
    st.write("Image Name :gear:")

if uploaded is not None:
    try:
        file_bytes = np.frombuffer(uploaded.read(), np.uint8)
        img = cv2.imdecode(file_bytes, cv2.IMREAD_COLOR)
        if img is None:
            st.error("Image cannot be decoded. Try another file please")
        else:
            st.image(img, channels="BGR", caption="Uploaded Image")

        with col1:
            st.image(img, use_column_width=True)

        img_resized = cv2.resize(img, (32, 32))
        img_input = np.expand_dims(img_resized / 255.0, axis=0)

        # model predicts
        with st.spinner("Analysing your Image"):
            predict = model.predict(img_input)
            class_id = int(np.argmax(predict))
            # predict_class = np.argmax(predict)
            class_name = Class_name[class_id]
            confidence = predict[0][class_id]

        with col2:
            st.success(f"Prediction: **({confidence*100:.2f}% Confidence)**")

    except Exception as e:
        st.error(f"Something went wrong{e}")
