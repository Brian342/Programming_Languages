import pandas as pd
import numpy as np
import streamlit as st


# show the admin view
def show_admin_view():
    st.title('Admin Control Panel')

    # High level metrics
    col1, col2, col3 = st.columns(3)
    col1.metric("Active Drivers", "12", "+2")
    col2.metric("Critical Alert", "1", "-50%")
    col3.metric("System Uptime", "99.9%")
    tabs = st.tabs(['Driver Logs', 'System Health'])
    with tabs[0]:
        st.subheader('Critical Alerts')

        Log_data = [
            {"Driver": "Brian", "Event": "Drowsiness Detected", "Time": "12:05 PM", "Severity": "High"},
            {"Driver": "John", "Event": "Safe", "Time": "12:10 PM", "Severity": "Low"},
            {"Driver": "Alice", "Event": "Distracted", "Time": "12:15 PM", "Severity": "Medium"}
        ]

        df = pd.DataFrame(Log_data)

        def colour_severity(val):
            color = "red" if val == "High" else ("orange" if val == "Medium" else "green")
            return f'Colour: {color}'

        st.dataframe(df.style.applymap(colour_severity, subset=['Severity']), use_container_width=True)

        with tabs[1]:
            st.subheader("System Performance")
            st.write("CPU Usage: 15%")
            st.progress(15)
            st.write("Camera Feed Latency: 45ms")



