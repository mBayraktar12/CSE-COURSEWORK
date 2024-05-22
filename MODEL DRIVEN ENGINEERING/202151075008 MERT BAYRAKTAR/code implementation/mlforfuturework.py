import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report, precision_score, recall_score
from sklearn.ensemble import RandomForestClassifier

# Step 1: Define the Data Schema
class DataSchema:
    def __init__(self):
        self.feature_columns = None
        self.label_column = None

    def set_feature_columns(self, columns):
        self.feature_columns = columns

    def set_label_column(self, column):
        self.label_column = column

# Step 2: Load and Preprocess the Data
def load_data(file_path):
    data = pd.read_csv(file_path)
    data.replace(0, np.nan, inplace=True)
    data.replace(np.nan, -1000, inplace=True)
    return data

# Step 3: Split the Data
def split_data(data, test_size=0.2, random_state=42):
    X = data[data_schema.feature_columns]
    y = data[data_schema.label_column]
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=test_size, random_state=random_state)
    return X_train, X_test, y_train, y_test

# Step 4: Define and Train the Model
def train_model(X_train, y_train, n_estimators=10):
    model = RandomForestClassifier(n_estimators=n_estimators, criterion='entropy', random_state=0)
    model.fit(X_train, y_train)
    return model

# Step 5: Make Predictions
def make_predictions(model, X_test):
    return model.predict(X_test)

# Step 6: Evaluate the Model
def evaluate_model(y_test, y_pred):
    accuracy = accuracy_score(y_test, y_pred)
    precision = precision_score(y_test, y_pred, average='weighted')
    recall = recall_score(y_test, y_pred, average='weighted')
    return accuracy, precision, recall

# Step 7: Print Evaluation Metrics
def print_evaluation_metrics(accuracy, precision, recall):
    print("Accuracy:", accuracy)
    print("Precision:", precision)
    print("Recall:", recall)

# Step 8: Define the Data Schema Instance
data_schema = DataSchema()
data_schema.set_feature_columns(range(85))
data_schema.set_label_column(88)

# Step 9: Load and Preprocess the Data
data = load_data('/content/drive/MyDrive/Colab Notebooks/INDOOR LOCALIZATION EXPERIMENTS/data_new.csv')

# Step 10: Split the Data
X_train, X_test, y_train, y_test = split_data(data)

# Step 11: Train the Model
model = train_model(X_train, y_train)

# Step 12: Make Predictions
y_pred = make_predictions(model, X_test)

# Step 13: Evaluate the Model
accuracy, precision, recall = evaluate_model(y_test, y_pred)

# Step 14: Print Evaluation Metrics
print_evaluation_metrics(accuracy, precision, recall)