import time
import os

def process_data(file_path):
    with open(file_path, 'r') as f:
        text = f.read()
    word_count = len(text.split())
    print(f"Processed {file_path}: {word_count} words")
    time.sleep(1)  # Simulate processing time

if __name__ == "__main__":
    input_dir = "path"
    output_dir ="path"

    os.makedirs(output_dir, exist_ok=True)

    for filename in os.listdir(input_dir):
        if filename.endswith(".txt"):
            file_path = os.path.join(input_dir, filename)
            
