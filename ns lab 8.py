import sqlite3

def get_user_data(username):
    # Connect to a dummy database
    conn = sqlite3.connect('users.db')
    cursor = conn.cursor()

    # SECURITY ISSUE: String formatting/concatenation used directly in the SQL query
    query = f"SELECT * FROM users WHERE username = '{username}'"
    
    print(f"Executing Query: {query}")
    
    try:
        cursor.execute(query)
        result = cursor.fetchall()
        return result
    except Exception as e:
        return str(e)
    finally:
        conn.close()

# Example of how an attacker might exploit this:
# If the user inputs: admin' OR '1'='1
# The query becomes: SELECT * FROM users WHERE username = 'admin' OR '1'='1'
# This bypasses the authentication check and returns all users.