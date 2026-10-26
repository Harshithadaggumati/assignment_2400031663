
import { useEffect, useState } from 'react';
import './App.css';

const API = 'http://localhost:2001/CourierBranch';

const emptyForm = {
  branchCode: '',
  city: '',
  managerName: '',
  dailyParcelLimit: ''
};

function App() {
  const [branches, setBranches] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [editId, setEditId] = useState(null);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  async function request(url, options = {}) {
    const response = await fetch(url, {
      ...options,
      headers: {
        'Content-Type': 'application/json',
        ...options.headers
      }
    });

    const text = await response.text();
    let data = null;

    try {
      data = text ? JSON.parse(text) : null;
    } catch {
      data = text;
    }

    if (!response.ok) {
      const message =
        data?.message ||
        (typeof data === 'string' ? data : 'Request failed');

      throw new Error(
        typeof message === 'string'
          ? message
          : JSON.stringify(message)
      );
    }

    return data;
  }

  async function loadBranches() {
    setLoading(true);
    setError('');

    try {
      const data = await request(`${API}/displayall`);
      setBranches(Array.isArray(data) ? data : []);
    } catch (err) {
      setError(`Could not load branches: ${err.message}`);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadBranches();
  }, []);

  function handleChange(event) {
    const { name, value } = event.target;
    setForm({ ...form, [name]: value });
  }

  async function handleSubmit(event) {
    event.preventDefault();
    setMessage('');
    setError('');

    const payload = {
      ...form,
      dailyParcelLimit: Number(form.dailyParcelLimit)
    };

    try {
      if (editId === null) {
        await request(`${API}/add`, {
          method: 'POST',
          body: JSON.stringify(payload)
        });

        setMessage('Courier branch added successfully!');
      } else {
        await request(`${API}/update/${editId}`, {
          method: 'PUT',
          body: JSON.stringify(payload)
        });

        setMessage('Courier branch updated successfully!');
      }

      setForm(emptyForm);
      setEditId(null);
      await loadBranches();
    } catch (err) {
      setError(err.message);
    }
  }

  function handleEdit(branch) {
    setEditId(branch.id);
    setForm({
      branchCode: branch.branchCode,
      city: branch.city,
      managerName: branch.managerName,
      dailyParcelLimit: String(branch.dailyParcelLimit)
    });

    setMessage('');
    setError('');
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  async function handleDelete(id) {
    if (!window.confirm('Are you sure you want to delete this branch?')) {
      return;
    }

    setMessage('');
    setError('');

    try {
      await request(`${API}/delete/${id}`, {
        method: 'DELETE'
      });

      setMessage('Courier branch deleted successfully!');
      await loadBranches();
    } catch (err) {
      setError(err.message);
    }
  }

  function handleCancel() {
    setForm(emptyForm);
    setEditId(null);
    setMessage('');
    setError('');
  }

  return (
    <div className="app-shell">
      <nav className="navbar navbar-dark bg-primary shadow-sm">
        <div className="container">
          <span className="navbar-brand fw-bold">
            Courier Branch Management
          </span>
          <span className="text-white small">Admin Dashboard</span>
        </div>
      </nav>

      <main className="container py-4 py-md-5">
        <div className="hero-section mb-4">
          <p className="text-primary fw-semibold mb-2">
            COURIER MANAGEMENT SYSTEM
          </p>
          <h1 className="fw-bold">Manage Your Branches</h1>
          <p className="text-secondary mb-0">
            Add, view, update and manage courier branch details.
          </p>
        </div>

        {message && (
          <div className="alert alert-success" role="status">
            {message}
          </div>
        )}

        {error && (
          <div className="alert alert-danger" role="alert">
            {error}
            {error.toLowerCase().includes('fetch') && (
              <div className="mt-2">
                Check that the backend is running on port 2001
                and allows requests from this frontend.
              </div>
            )}
          </div>
        )}

        <div className="row g-4">
          <div className="col-12 col-lg-4">
            <div className="card border-0 shadow-sm">
              <div className="card-body p-4">
                <h4 className="fw-bold mb-1">
                  {editId === null ? 'Add New Branch' : 'Update Branch'}
                </h4>
                <p className="text-secondary small mb-4">
                  Enter the branch details below.
                </p>

                <form onSubmit={handleSubmit}>
                  <div className="mb-3">
                    <label className="form-label">Branch Code</label>
                    <input
                      className="form-control"
                      name="branchCode"
                      value={form.branchCode}
                      onChange={handleChange}
                      placeholder="e.g. CB102"
                      maxLength={100}
                      required
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">City</label>
                    <input
                      className="form-control"
                      name="city"
                      value={form.city}
                      onChange={handleChange}
                      placeholder="Enter city"
                      required
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Manager Name</label>
                    <input
                      className="form-control"
                      name="managerName"
                      value={form.managerName}
                      onChange={handleChange}
                      placeholder="Enter manager name"
                      required
                    />
                  </div>

                  <div className="mb-4">
                    <label className="form-label">
                      Daily Parcel Limit
                    </label>
                    <input
                      className="form-control"
                      type="number"
                      name="dailyParcelLimit"
                      value={form.dailyParcelLimit}
                      onChange={handleChange}
                      min="1"
                      placeholder="e.g. 100"
                      required
                    />
                  </div>

                  <button
                    className="btn btn-primary w-100"
                    type="submit"
                  >
                    {editId === null ? 'Add Branch' : 'Save Changes'}
                  </button>

                  {editId !== null && (
                    <button
                      className="btn btn-outline-secondary w-100 mt-2"
                      type="button"
                      onClick={handleCancel}
                    >
                      Cancel Edit
                    </button>
                  )}
                </form>
              </div>
            </div>
          </div>

          <div className="col-12 col-lg-8">
            <div className="card border-0 shadow-sm">
              <div className="card-body p-4">
                <div className="d-flex justify-content-between align-items-center gap-3 mb-3">
                  <div>
                    <h4 className="fw-bold mb-1">Branch Directory</h4>
                    <p className="text-secondary small mb-0">
                      Total branches: {branches.length}
                    </p>
                  </div>

                  <button
                    className="btn btn-outline-primary btn-sm"
                    onClick={loadBranches}
                    type="button"
                  >
                    Refresh
                  </button>
                </div>

                {loading ? (
                  <p className="text-secondary py-4 text-center">
                    Loading branches...
                  </p>
                ) : branches.length === 0 ? (
                  <p className="text-secondary py-4 text-center">
                    No branches found. Add your first branch!
                  </p>
                ) : (
                  <div className="table-responsive">
                    <table className="table table-hover align-middle">
                      <thead className="table-light">
                        <tr>
                          <th>ID</th>
                          <th>Branch Details</th>
                          <th>Manager</th>
                          <th>Daily Limit</th>
                          <th>Actions</th>
                        </tr>
                      </thead>

                      <tbody>
                        {branches.map((branch) => (
                          <tr key={branch.id}>
                            <td>{branch.id}</td>
                            <td>
                              <div className="fw-semibold">
                                {branch.branchCode}
                              </div>
                              <div className="small text-secondary">
                                {branch.city}
                              </div>
                            </td>
                            <td>{branch.managerName}</td>
                            <td>{branch.dailyParcelLimit}</td>
                            <td>
                              <div className="d-flex gap-2">
                                <button
                                  className="btn btn-sm btn-outline-primary"
                                  onClick={() => handleEdit(branch)}
                                  type="button"
                                >
                                  Edit
                                </button>
                                <button
                                  className="btn btn-sm btn-outline-danger"
                                  onClick={() => handleDelete(branch.id)}
                                  type="button"
                                >
                                  Delete
                                </button>
                              </div>
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                )}
              </div>
            </div>
          </div>
        </div>
      </main>

      <footer className="text-center text-secondary py-4 small">
        Courier Branch Management System
      </footer>
    </div>
  );
}

export default App;
