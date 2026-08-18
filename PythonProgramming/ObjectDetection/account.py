# import streamlit as st
# import firebase_admin
# from firebase_admin import credentials, auth, _apps, exceptions
# import json
# import os
#
# LOCAL_KEY_PATH = "wakeapp-51082-firebase-adminsdk-fbsvc-97a897121b.json"
#
# if "FIREBASE_SERVICE_ACCOUNT_JSON" in st.secrets:
#     st.info("Using Streamlit Secrets for Firebase Connection.")
#     try:
#         service_account_info = json.loads(st.secrets["FIREBASE_SERVICE_ACCOUNT_JSON"])
#         cred = credentials.Certificate(service_account_info)
#     except Exception as e:
#         st.error(f"Error loading JSON from secrets. Check your .streamlit/secrets.toml file. Error: {e}")
#         st.stop()
# else:
#     st.info("Using Local file path for Firebase Connection")
#     try:
#         cred = credentials.Certificate(LOCAL_KEY_PATH)
#     except FileNotFoundError:
#         st.error(f"FATAL ERROR: Local key file not found at {LOCAL_KEY_PATH}. Ensure the path is correct.")
#         st.stop()
#
# # Initialize the app with the credentials
# if not _apps:
#     try:
#         firebase_admin.initialize_app(cred)
#     except Exception as e:
#         st.error(f"Firebase Initialization Failed: {e}")
#         st.stop()
#
#
# def credential():
#     st.title('Welcome to :blue[WakeApp]')
#
#     choose = st.selectbox('Login/SignUp', ['Login', 'Sign Up'])
#
#     if choose == 'Login':
#         email = st.text_input('Email address', key='login_email')
#         password = st.text_input('Password', type='password', key='login_password')
#
#         if st.button('Login'):
#             if not email or not password:
#                 st.warning("Email and Password are required.")
#                 return
#
#             try:
#                 # This checks existence, not password validity!
#                 auth.get_user_by_email(email)
#                 st.success("Login Successful (User found in database)!")
#             except firebase_admin.exceptions.NotFoundError:
#                 st.error('Login Failed: User not found.')
#             except Exception as e:
#                 st.error(f'Login Failed due to an error: {e}')
#
#     else:  # Sign Up
#         email = st.text_input('Email address', key='signup_email')
#         password = st.text_input('Password', type='password', key='signup_password')
#         userName = st.text_input('Enter your Unique UserName', key='signup_username')
#
#         if st.button('Create Account'):
#             if not all([email, password, userName]):
#                 st.warning("All fields are required to create an account.")
#                 return
#
#             try:
#                 # Auth.create_user handles the signup
#                 auth.create_user(email=email, password=password, uid=userName)
#
#                 st.success('Account Created Successfully!')
#                 st.markdown('Please Login Using Your Email and Password!')
#                 st.balloons()
#
#             except firebase_admin.exceptions.EmailAlreadyExistsError:
#                 st.error("Account Creation Failed: This email address is already in use.")
#             except firebase_admin.exceptions.InvalidPasswordError:
#                 st.error("Account Creation Failed: Password must be at least 6 characters long.")
#             except Exception as e:
#                 st.error(f"Account Creation Failed: {e}")
#





import streamlit as st
import firebase_admin
from firebase_admin import credentials, auth, initialize_app, exceptions
import asyncio
import json
import os
from httpx_oauth.clients.google import GoogleOAuth2

# initialize Firebase app
cred = credentials.Certificate('wakeapp-51082-815833840b68.json')
try:
    firebase_admin.get_app()
except ValueError as e:
    initialize_app(cred)

# initialize Google OAuth2 client
client_id = st.secrets["client_id"]
client_secret = st.secrets["client_secret"]
redirect_url = "http://localhost:8501/"

client = GoogleOAuth2(client_id=client_id, client_secret=client_secret)

# st.session_state.email = ''


# getting the access and token function
async def get_access_token(client: GoogleOAuth2, redirect_url: str, code: str):
    return await client.get_access_token(code, redirect_url)


async def get_email(client: GoogleOAuth2, token: str):
    user_id, user_email = await client.get_id_email(token)
    return user_id, user_email


def get_logged_in_user_email():
    try:
        query_params = st.query_params()
        code = query_params.get('code')
        if code:
            token = asyncio.run(get_access_token(client, redirect_url, code))
            st.query_params()

        if token:
            user_id, user_email = asyncio.run(get_email(client, token['access_token']))
            if user_email:
                try:
                    user = auth.get_user_by_email(user_email)
                except exceptions.FirebaseError:
                    user = auth.create_user(email=user_email)
                st.session_state.email = user.email
                st.rerun()
                return user.email
        return None
    except:
        pass


# show logging button
def show_login_button():
    authorization_url = asyncio.run(client.get_authorization_url(
        redirect_url,
        scope=["email", "profile"],
        extras_params={"access_type": "offline"},
    ))
    st.markdown(f'<a href="{authorization_url}" target="_self">Login</a>', unsafe_allow_html=True)
    get_logged_in_user_email()



def app():
    if not st.session_state.email:
        st.title('Welcome')
        get_logged_in_user_email()
        if not st.session_state.email:
            show_login_button()
    else:
        # What the user sees in the "Profile" tab once logged in
        st.write(f"Logged in as: **{st.session_state.email}**")
        if st.button("Log Out", type="secondary"):
            st.session_state.email = ''
            st.rerun()




