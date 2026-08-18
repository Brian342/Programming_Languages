"""
Research Paper Discovery UI — Streamlit App
============================================
Run with:
    streamlit run app.py

Requires:
    pip install streamlit pandas numpy scikit-learn sentence-transformers torch
"""

import warnings

warnings.filterwarnings("ignore")

import numpy as np
import pandas as pd
import streamlit as st
import os
from datetime import datetime, timedelta
from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics.pairwise import cosine_similarity
from sentence_transformers import SentenceTransformer

# ─────────────────────────────────────────────
# PAGE CONFIG
# ─────────────────────────────────────────────
st.set_page_config(
    page_title="Research Lens",
    layout="wide",
    initial_sidebar_state="expanded",
)

# ─────────────────────────────────────────────
# CUSTOM CSS
# ─────────────────────────────────────────────
st.markdown("""
<style>
@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display:ital@0;1&family=DM+Sans:wght@300;400;500;600&display=swap');

html, body, [class*="css"] {
    font-family: 'DM Sans', sans-serif;
}

/* App background */
.stApp {
    background: #0d0f14;
    color: #e8e6e0;
}

/* Sidebar */
section[data-testid="stSidebar"] {
    background: #13161d;
    border-right: 1px solid #1e2330;
}
section[data-testid="stSidebar"] * {
    color: #c5c2bb !important;
}

/* Header */
.app-header {
    text-align: center;
    padding: 2.5rem 0 1.5rem;
}
.app-header h1 {
    font-family: 'DM Serif Display', serif;
    font-size: 3.2rem;
    font-weight: 400;
    color: #f0ede6;
    letter-spacing: -0.5px;
    margin: 0;
    line-height: 1.1;
}
.app-header h1 em {
    font-style: italic;
    color: #c8a96e;
}
.app-header p {
    color: #888480;
    font-size: 1rem;
    font-weight: 300;
    margin-top: 0.5rem;
    letter-spacing: 0.3px;
}

/* Search bar */
.stTextInput > div > div > input {
    background: #1a1d26 !important;
    border: 1px solid #2a2e3d !important;
    border-radius: 12px !important;
    color: #f0ede6 !important;
    font-size: 1.05rem !important;
    padding: 0.75rem 1.2rem !important;
    font-family: 'DM Sans', sans-serif !important;
}
.stTextInput > div > div > input:focus {
    border-color: #c8a96e !important;
    box-shadow: 0 0 0 2px rgba(200,169,110,0.15) !important;
}
.stTextInput > div > div > input::placeholder {
    color: #555 !important;
}

/* Buttons */
.stButton > button {
    background: #c8a96e !important;
    color: #0d0f14 !important;
    border: none !important;
    border-radius: 10px !important;
    font-family: 'DM Sans', sans-serif !important;
    font-weight: 600 !important;
    font-size: 0.95rem !important;
    padding: 0.6rem 1.8rem !important;
    transition: all 0.2s ease !important;
    letter-spacing: 0.2px;
}
.stButton > button:hover {
    background: #dbbf82 !important;
    transform: translateY(-1px);
}

/* Sliders */
.stSlider > div > div > div > div {
    background: #c8a96e !important;
}

/* Select boxes */
.stSelectbox > div > div {
    background: #1a1d26 !important;
    border: 1px solid #2a2e3d !important;
    border-radius: 10px !important;
    color: #f0ede6 !important;
}

/* Paper result card */
.paper-card {
    background: #13161d;
    border: 1px solid #1e2330;
    border-radius: 14px;
    padding: 1.4rem 1.6rem;
    margin-bottom: 1rem;
    transition: border-color 0.2s ease;
    position: relative;
}
.paper-card:hover {
    border-color: #c8a96e44;
}
.paper-rank {
    position: absolute;
    top: 1.2rem;
    right: 1.4rem;
    font-family: 'DM Serif Display', serif;
    font-size: 1.8rem;
    color: #2a2e3d;
    font-weight: 400;
    line-height: 1;
}
.paper-title {
    font-family: 'DM Serif Display', serif;
    font-size: 1.15rem;
    color: #f0ede6;
    font-weight: 400;
    line-height: 1.35;
    margin-bottom: 0.5rem;
    padding-right: 3rem;
}
.paper-meta {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
    margin-bottom: 0.75rem;
    align-items: center;
}
.badge {
    font-size: 0.72rem;
    font-weight: 500;
    padding: 0.2rem 0.65rem;
    border-radius: 20px;
    letter-spacing: 0.3px;
}
.badge-year {
    background: #1e2a1e;
    color: #6abf6a;
    border: 1px solid #2e4a2e;
}
.badge-cat {
    background: #1e2230;
    color: #6a9abf;
    border: 1px solid #2a3450;
}
.badge-author {
    background: #2a1e1e;
    color: #bf8a6a;
    border: 1px solid #4a2e2e;
}
.paper-summary {
    font-size: 0.875rem;
    color: #888480;
    line-height: 1.65;
    font-weight: 300;
}
.score-row {
    display: flex;
    gap: 1.2rem;
    margin-top: 0.9rem;
    padding-top: 0.9rem;
    border-top: 1px solid #1e2330;
}
.score-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 2px;
}
.score-label {
    font-size: 0.65rem;
    color: #555;
    text-transform: uppercase;
    letter-spacing: 0.8px;
    font-weight: 500;
}
.score-value {
    font-size: 0.9rem;
    color: #c8a96e;
    font-weight: 600;
}

/* Section headers */
.section-label {
    font-size: 0.7rem;
    text-transform: uppercase;
    letter-spacing: 1.5px;
    color: #555;
    font-weight: 500;
    margin-bottom: 1rem;
    margin-top: 0.5rem;
}

/* Stats bar */
.stats-bar {
    display: flex;
    gap: 2rem;
    padding: 1rem 1.5rem;
    background: #13161d;
    border-radius: 12px;
    border: 1px solid #1e2330;
    margin-bottom: 1.5rem;
}
.stat-item { display: flex; flex-direction: column; gap: 2px; }
.stat-number {
    font-family: 'DM Serif Display', serif;
    font-size: 1.5rem;
    color: #c8a96e;
}
.stat-label { font-size: 0.72rem; color: #555; text-transform: uppercase; letter-spacing: 0.8px; }

/* Divider */
hr { border-color: #1e2330 !important; }

/* Multiselect */
.stMultiSelect > div > div {
    background: #1a1d26 !important;
    border: 1px solid #2a2e3d !important;
    border-radius: 10px !important;
}

/* Hide streamlit branding */
#MainMenu, footer, header { visibility: hidden; }

/* Tab styling */
.stTabs [data-baseweb="tab-list"] {
    background: #13161d;
    border-radius: 10px;
    padding: 4px;
    gap: 4px;
    border: 1px solid #1e2330;
}
.stTabs [data-baseweb="tab"] {
    background: transparent !important;
    color: #888 !important;
    border-radius: 8px !important;
    font-family: 'DM Sans', sans-serif !important;
    font-size: 0.88rem !important;
    font-weight: 500 !important;
}
.stTabs [aria-selected="true"] {
    background: #c8a96e !important;
    color: #0d0f14 !important;
}

/* Metric cards */
.metric-card {
    background: #13161d;
    border: 1px solid #1e2330;
    border-radius: 12px;
    padding: 1rem 1.2rem;
    text-align: center;
}
</style>
""", unsafe_allow_html=True)

# ─────────────────────────────────────────────
# CONSTANTS
# ─────────────────────────────────────────────
EMBEDDING_MODEL = "all-MiniLM-L6-v2"
HALF_LIFE_DAYS = 365


# ─────────────────────────────────────────────
# CACHED BACKEND FUNCTIONS
# ─────────────────────────────────────────────
@st.cache_data(show_spinner=False)
def load_and_prepare(path: str) -> pd.DataFrame:
    df = pd.read_csv(path)

    for col in ["Published Date", "Updated Date"]:
        if col in df.columns:
            df[col] = pd.to_datetime(df[col], errors="coerce")

    df["Summary"] = df["Summary"].fillna("")
    df["Title"] = df["Title"].fillna("")
    df["Authors"] = df["Authors"].fillna("")
    df["First Author"] = df["First Author"].fillna("")

    if "Summary Word Count" not in df.columns:
        df["Summary Word Count"] = df["Summary"].apply(lambda x: len(str(x).split()))

    # Feature engineering
    today = datetime.now()
    df["days_since_published"] = (today - df["Published Date"]).dt.days.clip(lower=0)
    df["recency_score"] = np.exp(-df["days_since_published"] / (HALF_LIFE_DAYS / np.log(2)))

    author_counts = df["First Author"].value_counts()
    df["author_paper_count"] = df["First Author"].map(author_counts)
    df["summary_depth"] = df["Summary Word Count"].clip(upper=500)

    if "Updated Date" in df.columns:
        cutoff = today - timedelta(days=180)
        df["update_bonus"] = (df["Updated Date"] >= cutoff).astype(float)
    else:
        df["update_bonus"] = 0.0

    scaler = MinMaxScaler()
    cols = ["recency_score", "author_paper_count", "summary_depth"]
    df[cols] = scaler.fit_transform(df[cols].fillna(0))
    df.rename(columns={"author_paper_count": "author_score"}, inplace=True)

    # Extract year
    df["year"] = df["Published Date"].dt.year

    return df


@st.cache_resource(show_spinner=False)
def load_model():
    return SentenceTransformer(EMBEDDING_MODEL)


EMBEDDING_PATH = "Documents/programmingLanguages/PythonProgramming/Academic-Research/paper_embeddings.npy"


@st.cache_data(show_spinner=False)
def compute_embeddings(_df: pd.DataFrame, _model) -> np.ndarray:
    if os.path.exists(EMBEDDING_PATH):
        return np.load(EMBEDDING_PATH)

    texts = (_df["Title"] + ". " + _df["Summary"]).tolist()
    embeddings = _model.encode(texts, batch_size=64, show_progress_bar=False,
                               convert_to_numpy=True)
    np.save(EMBEDDING_PATH, embeddings)


def search_papers(query, df, embeddings, model, weights, top_k,
                  category_filter=None, year_range=None):
    q_emb = model.encode([query], convert_to_numpy=True)
    sims = cosine_similarity(q_emb, embeddings)[0]
    s_min, s_max = sims.min(), sims.max()
    sims_norm = (sims - s_min) / (s_max - s_min) if s_max > s_min else sims

    working = df.copy()
    working["similarity"] = sims_norm
    working["composite_score"] = (
            weights["similarity"] * working["similarity"] +
            weights["recency"] * working["recency_score"] +
            weights["author_score"] * working["author_score"] +
            weights["summary_depth"] * working["summary_depth"] +
            weights["update_bonus"] * working["update_bonus"]
    )

    if category_filter and category_filter != "All":
        mask = (
                working["Category"].str.contains(category_filter, case=False, na=False) |
                working["Category Code"].str.contains(category_filter, case=False, na=False)
        )
        working = working[mask]

    if year_range:
        working = working[
            (working["year"] >= year_range[0]) &
            (working["year"] <= year_range[1])
            ]

    return (
        working.sort_values("composite_score", ascending=False)
        .head(top_k)
        .reset_index(drop=True)
    )


def recommend_similar(paper_idx, df, embeddings, top_n=5):
    seed = embeddings[paper_idx].reshape(1, -1)
    sims = cosine_similarity(seed, embeddings)[0]
    sims[paper_idx] = -1.0
    top_idx = np.argsort(sims)[::-1][:top_n]
    recs = df.iloc[top_idx].copy()
    recs["similarity_score"] = sims[top_idx].round(4)
    return recs.reset_index(drop=True)


# ─────────────────────────────────────────────
# RENDER HELPERS
# ─────────────────────────────────────────────
def render_paper_card(row, rank, show_scores=True, df=None, embeddings=None, model=None):
    year = int(row["year"]) if pd.notna(row.get("year")) else "N/A"
    category = row.get("Category", "Unknown")
    author = row.get("First Author", "Unknown")
    summary = str(row.get("Summary", ""))
    summary_preview = summary[:320] + "…" if len(summary) > 320 else summary

    score_html = ""
    if show_scores:
        score_html = f"""
        <div class="score-row">
            <div class="score-item">
                <span class="score-label">Overall</span>
                <span class="score-value">{row.get('composite_score', 0):.3f}</span>
            </div>
            <div class="score-item">
                <span class="score-label">Relevance</span>
                <span class="score-value">{row.get('similarity', 0):.3f}</span>
            </div>
            <div class="score-item">
                <span class="score-label">Recency</span>
                <span class="score-value">{row.get('recency_score', 0):.3f}</span>
            </div>
            <div class="score-item">
                <span class="score-label">Author</span>
                <span class="score-value">{row.get('author_score', 0):.3f}</span>
            </div>
        </div>
        """

    st.markdown(f"""
    <div class="paper-card">
        <div class="paper-rank">#{rank}</div>
        <div class="paper-title">{row['Title']}</div>
        <div class="paper-meta">
            <span class="badge badge-year">{year}</span>
            <span class="badge badge-cat">{category}</span>
            <span class="badge badge-author">✍ {author}</span>
        </div>
        <div class="paper-summary">{summary_preview}</div>
        {score_html}
    </div>
    """, unsafe_allow_html=True)


# ─────────────────────────────────────────────
# SIDEBAR
# ─────────────────────────────────────────────
with st.sidebar:
    st.markdown("### Settings")
    st.markdown("---")

    DEFAULT_PATH = "/Users/briankimanzi/Documents/programmingLanguages/PythonProgramming/Academic-Research/Research paper csv.csv"
    dataset_path = st.text_input(
        "Dataset path",
        value=DEFAULT_PATH,
        help="Path to your CSV file"
    )

    st.markdown("#### Result options")
    top_k = st.slider("Number of results", min_value=5, max_value=50, value=10, step=5)

    st.markdown("#### Ranking weights")
    st.caption("Adjust how much each signal contributes to ranking.")

    w_sim = st.slider("Relevance (semantic)", 0.0, 1.0, 0.45, 0.05)
    w_rec = st.slider("Recency", 0.0, 1.0, 0.25, 0.05)
    w_auth = st.slider("Author productivity", 0.0, 1.0, 0.15, 0.05)
    w_sum = st.slider("Abstract depth", 0.0, 1.0, 0.10, 0.05)
    w_upd = st.slider("Update bonus", 0.0, 1.0, 0.05, 0.05)

    total = w_sim + w_rec + w_auth + w_sum + w_upd
    if abs(total - 1.0) > 0.01:
        st.warning(f"Weights sum to {total:.2f} (ideally 1.0). Results may vary.")

    WEIGHTS = {
        "similarity": w_sim,
        "recency": w_rec,
        "author_score": w_auth,
        "summary_depth": w_sum,
        "update_bonus": w_upd,
    }

    st.markdown("---")
    show_scores = st.toggle("Show score breakdown", value=True)

# ─────────────────────────────────────────────
# MAIN CONTENT
# ─────────────────────────────────────────────
st.markdown("""
<div class="app-header">
    <h1>Research <em>Lens</em></h1>
    <p>Discover the most relevant and recent academic papers — ranked by what matters</p>
</div>
""", unsafe_allow_html=True)
try:
    with st.spinner("Loading dataset…"):
        df = load_and_prepare(dataset_path)

    with st.spinner("Loading embedding model…"):
        model = load_model()

    with st.spinner("Building paper embeddings (first run may take a moment)…"):
        embeddings = compute_embeddings(df, model)

except FileNotFoundError:
    st.error(f"Dataset not found at `{dataset_path}`. Update the path in the sidebar.")
    st.stop()

# ── Dataset stats ─────────────────────────────────────────────────
year_min = int(df["year"].min()) if df["year"].notna().any() else 2000
year_max = int(df["year"].max()) if df["year"].notna().any() else 2024
categories = ["All"] + sorted(df["Category"].dropna().unique().tolist())

st.markdown(f"""
<div class="stats-bar">
    <div class="stat-item">
        <span class="stat-number">{len(df):,}</span>
        <span class="stat-label">Total papers</span>
    </div>
    <div class="stat-item">
        <span class="stat-number">{df['Category'].nunique()}</span>
        <span class="stat-label">Categories</span>
    </div>
    <div class="stat-item">
        <span class="stat-number">{df['First Author'].nunique():,}</span>
        <span class="stat-label">Authors</span>
    </div>
    <div class="stat-item">
        <span class="stat-number">{year_min}–{year_max}</span>
        <span class="stat-label">Year range</span>
    </div>
</div>
""", unsafe_allow_html=True)

# ── Search bar ────────────────────────────────────────────────────
col1, col2 = st.columns([4, 1])
with col1:
    query = st.text_input(
        "",
        placeholder="Search papers… e.g. 'deep learning for NLP', 'transformer attention'",
        label_visibility="collapsed"
    )
with col2:
    search_clicked = st.button("Search", use_container_width=True)

# ── Filters ───────────────────────────────────────────────────────
with st.expander("Filters", expanded=False):
    fcol1, fcol2 = st.columns(2)
    with fcol1:
        category_filter = st.selectbox("Category", categories)
    with fcol2:
        year_range = st.slider(
            "Publication year",
            min_value=year_min,
            max_value=year_max,
            value=(year_min, year_max)
        )

# ─────────────────────────────────────────────
# SEARCH & RESULTS
# ─────────────────────────────────────────────
if (search_clicked or query) and query.strip():
    with st.spinner(f"Ranking papers for '{query}'…"):
        results = search_papers(
            query=query,
            df=df,
            embeddings=embeddings,
            model=model,
            weights=WEIGHTS,
            top_k=top_k,
            category_filter=category_filter if category_filter != "All" else None,
            year_range=year_range,
        )

    if results.empty:
        st.warning("No papers found. Try a different query or remove filters.")
    else:
        tab1, tab2 = st.tabs([f"Results ({len(results)})", "Data table"])

        # ── Tab 1: Card view ──────────────────────────────────────
        with tab1:
            st.markdown(f'<div class="section-label">Top {len(results)} papers for "{query}"</div>',
                        unsafe_allow_html=True)
            for i, (_, row) in enumerate(results.iterrows()):
                render_paper_card(row, rank=i + 1, show_scores=show_scores)

                # Recommend button per paper
                with st.expander(f"Find papers similar to #{i + 1}", expanded=False):
                    orig_idx = df.index[df["ID"] == row["ID"]].tolist()
                    if orig_idx:
                        recs = recommend_similar(orig_idx[0], df, embeddings, top_n=5)
                        for j, (_, rec_row) in enumerate(recs.iterrows()):
                            render_paper_card(rec_row, rank=j + 1, show_scores=False)

        # ── Tab 2: Table view ─────────────────────────────────────
        with tab2:
            display_df = results[[
                "Title", "First Author", "year", "Category",
                "composite_score", "similarity", "recency_score"
            ]].copy()
            display_df.columns = [
                "Title", "First Author", "Year", "Category",
                "Score", "Relevance", "Recency"
            ]
            display_df.index = range(1, len(display_df) + 1)
            display_df["Score"] = display_df["Score"].round(4)
            display_df["Relevance"] = display_df["Relevance"].round(4)
            display_df["Recency"] = display_df["Recency"].round(4)

            st.dataframe(
                display_df,
                use_container_width=True,
                height=500,
            )

            # Download button
            csv = display_df.to_csv(index=True).encode("utf-8")
            st.download_button(
                label="Download results as CSV",
                data=csv,
                file_name=f"results_{query.replace(' ', '_')}.csv",
                mime="text/csv",
            )

else:
    # Empty state
    st.markdown("""
    <div style="text-align:center; padding: 4rem 2rem; color: #444;">
        <div style="font-size: 3rem; margin-bottom: 1rem;"></div>
        <div style="font-family: 'DM Serif Display', serif; font-size: 1.3rem; color: #666;">
            Enter a query above to discover papers
        </div>
        <div style="font-size: 0.85rem; margin-top: 0.5rem; color: #444;">
            Results are ranked by relevance, recency, and author quality
        </div>
    </div>
    """, unsafe_allow_html=True)
